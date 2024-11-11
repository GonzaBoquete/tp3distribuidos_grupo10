package com.stockearte.tp3_grupo10.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.CatalogoRepository;

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	@Qualifier("catalogoRepository")
	private CatalogoRepository catalogoRepository;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ProductoService productoService;

	@Override
	public Catalogo add(Long idTienda) {
		Tienda tienda = getTiendaService().getOneById(idTienda);
		if (tienda != null) {
			Catalogo catalogo = new Catalogo();
			catalogo.setTienda(tienda);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
	}

	@Override
	public Catalogo updateTienda(Long idCatalogo, Long codigoTienda) {
		Catalogo catalogo = this.getOneById(idCatalogo);
		if (catalogo != null) {
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				catalogo.setTienda(tienda);
				return getCatalogoRepository().save(catalogo);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo agregarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().add(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo eliminarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().remove(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public void delete(Long idCatalogo) {
		Optional<Catalogo> foundCatalogo = getCatalogoRepository().findById(idCatalogo);
		if (!foundCatalogo.isEmpty()) {
			getCatalogoRepository().delete(foundCatalogo.get());
		}
	}

	@Override
	public Catalogo getOneById(Long idCatalogo) {
		Optional<Catalogo> catalogo = getCatalogoRepository().findById(idCatalogo);
		if (catalogo.isEmpty()) {
			throw new ServiceException("No se encontro el catalogo");
		}
		return catalogo.isEmpty() ? null : catalogo.get();
	}

	@Override
	public List<Catalogo> getAll() {
		return (List<Catalogo>) getCatalogoRepository().findAll();
	}

	@Override
	public byte[] exportCatalogosToPdf() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);

		try {
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();

			// Título principal
			Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
			Paragraph title = new Paragraph("Listado de Catálogos", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			document.add(new Paragraph(" "));

			// Obtener los catálogos
			List<Catalogo> catalogos = catalogoRepository.findAll();
			for (Catalogo catalogo : catalogos) {
				// Título de cada catálogo
				Font catalogTitleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
				Paragraph catalogTitle = new Paragraph("Catálogo: " + catalogo.getId(), catalogTitleFont);
				document.add(catalogTitle);

				// Crear la tabla para los productos del catálogo
				PdfPTable table = new PdfPTable(4); // 4 columnas: Producto, Colores, Talles, Foto
				table.setWidthPercentage(100);
				table.setSpacingBefore(10f);

				// Encabezados de la tabla
				Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
				PdfPCell header1 = new PdfPCell(new Phrase("Producto", headerFont));
				PdfPCell header2 = new PdfPCell(new Phrase("Colores", headerFont));
				PdfPCell header3 = new PdfPCell(new Phrase("Talles", headerFont));
				PdfPCell header4 = new PdfPCell(new Phrase("Foto", headerFont));

				table.addCell(header1);
				table.addCell(header2);
				table.addCell(header3);
				table.addCell(header4);

				// Agregar cada producto del catálogo
				for (Producto producto : catalogo.getProducto()) {
					// Producto
					table.addCell(producto.getNombre());

					// Colores
					String colores = String.join(", ", producto.getColor());
					table.addCell(colores);

					// Talles
					String talles = String.join(", ", producto.getTalle());
					table.addCell(talles);

					// Foto
					if (producto.getFoto() != null) {
						try {
							Image foto = Image.getInstance(producto.getFoto());
							foto.scaleToFit(50, 50); // Ajustar el tamaño de la foto
							PdfPCell fotoCell = new PdfPCell(foto, true);
							fotoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(fotoCell);
						} catch (Exception e) {
							table.addCell("No disponible");
						}
					} else {
						table.addCell("No disponible");
					}
				}

				document.add(table);
				document.add(new Paragraph(" "));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		return byteArrayOutputStream.toByteArray();
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public CatalogoRepository getCatalogoRepository() {
		return catalogoRepository;
	}

	public void setCatalogoRepository(CatalogoRepository catalogoRepository) {
		this.catalogoRepository = catalogoRepository;
	}

}
